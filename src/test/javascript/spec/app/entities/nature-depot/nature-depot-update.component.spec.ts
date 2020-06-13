import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { NatureDepotUpdateComponent } from 'app/entities/nature-depot/nature-depot-update.component';
import { NatureDepotService } from 'app/entities/nature-depot/nature-depot.service';
import { NatureDepot } from 'app/shared/model/nature-depot.model';

describe('Component Tests', () => {
  describe('NatureDepot Management Update Component', () => {
    let comp: NatureDepotUpdateComponent;
    let fixture: ComponentFixture<NatureDepotUpdateComponent>;
    let service: NatureDepotService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [NatureDepotUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(NatureDepotUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NatureDepotUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NatureDepotService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NatureDepot(123);
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
        const entity = new NatureDepot();
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
