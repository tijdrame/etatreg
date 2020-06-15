import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { BduDepotUpdateComponent } from 'app/entities/bdu-depot/bdu-depot-update.component';
import { BduDepotService } from 'app/entities/bdu-depot/bdu-depot.service';
import { BduDepot } from 'app/shared/model/bdu-depot.model';

describe('Component Tests', () => {
  describe('BduDepot Management Update Component', () => {
    let comp: BduDepotUpdateComponent;
    let fixture: ComponentFixture<BduDepotUpdateComponent>;
    let service: BduDepotService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [BduDepotUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BduDepotUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BduDepotUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BduDepotService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BduDepot(123);
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
        const entity = new BduDepot();
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
