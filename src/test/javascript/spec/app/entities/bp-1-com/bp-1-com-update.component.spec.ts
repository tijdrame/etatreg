import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { Bp1ComUpdateComponent } from 'app/entities/bp-1-com/bp-1-com-update.component';
import { Bp1ComService } from 'app/entities/bp-1-com/bp-1-com.service';
import { Bp1Com } from 'app/shared/model/bp-1-com.model';

describe('Component Tests', () => {
  describe('Bp1Com Management Update Component', () => {
    let comp: Bp1ComUpdateComponent;
    let fixture: ComponentFixture<Bp1ComUpdateComponent>;
    let service: Bp1ComService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [Bp1ComUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(Bp1ComUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(Bp1ComUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(Bp1ComService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Bp1Com(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Bp1Com();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
