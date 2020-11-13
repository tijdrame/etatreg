import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { BpsectinstUpdateComponent } from 'app/entities/bpsectinst/bpsectinst-update.component';
import { BpsectinstService } from 'app/entities/bpsectinst/bpsectinst.service';
import { Bpsectinst } from 'app/shared/model/bpsectinst.model';

describe('Component Tests', () => {
  describe('Bpsectinst Management Update Component', () => {
    let comp: BpsectinstUpdateComponent;
    let fixture: ComponentFixture<BpsectinstUpdateComponent>;
    let service: BpsectinstService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [BpsectinstUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BpsectinstUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BpsectinstUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BpsectinstService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Bpsectinst(123);
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
        const entity = new Bpsectinst();
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
